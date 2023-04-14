package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ContactViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private  val dbcontacts = FirebaseDatabase.getInstance().getReference(NODE_CONTACTS)

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> get() = _contact

    fun addContact(contact: Contact){
        val userId = auth.currentUser?.uid

        if (userId != null) {
            // Generate a unique ID for the new contact
            val contactId = dbcontacts.child(userId).push().key

            // Set the ID of the new contact
            contact.id = contactId

            // Save the new contact under the user ID in Firebase
            dbcontacts.child(userId).child(contactId!!).setValue(contact)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        _result.value = null
                    } else {
                        _result.value = it.exception
                    }
                }
        } else {
            _result.value = Exception("User not authenticated.")
        }
    }

    private val childEventListener = object: ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val contact = snapshot.getValue(Contact::class.java)
            val user = auth.currentUser?.uid
            contact?.id = snapshot.key
            _contact.value = contact!!
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}

    }

    fun getRealTimeUpdate(){
        val userId = auth.currentUser?.uid
        if (userId != null) {
            dbcontacts.child(userId).addChildEventListener(childEventListener)
        }
    }

    override fun onCleared(){
        super.onCleared()

        val userId = auth.currentUser?.uid
        if (userId != null){
            dbcontacts.child(userId).removeEventListener(childEventListener)
        }
    }
}