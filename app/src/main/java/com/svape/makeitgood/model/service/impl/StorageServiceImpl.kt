package com.svape.makeitgood.model.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.svape.makeitgood.model.Task
import com.svape.makeitgood.model.service.AccountService
import com.svape.makeitgood.model.service.StorageService
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.snapshots
import com.svape.makeitgood.model.service.trace
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class StorageServiceImpl
@Inject
constructor(private val firestore: FirebaseFirestore, private val auth: AccountService) :
    StorageService {

    override val tasks: Flow<List<Task>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                currentCollection(user.id).snapshots().map { snapshot -> snapshot.toObjects() }
            }

    override suspend fun getTask(taskId: String): Task? =
        currentCollection(auth.currentUserId).document(taskId).get().await().toObject()

    override suspend fun save(task: Task): String =
        trace(SAVE_TASK_TRACE) { currentCollection(auth.currentUserId).add(task).await().id }

    override suspend fun update(task: Task): Unit =
        trace(UPDATE_TASK_TRACE) {
            currentCollection(auth.currentUserId).document(task.id).set(task).await()
        }

    override suspend fun delete(taskId: String) {
        currentCollection(auth.currentUserId).document(taskId).delete().await()
    }


    // Deleting collections from an Android client is not recommended.
    override suspend fun deleteAllForUser(userId: String) {
        val matchingTasks = currentCollection(userId).get().await()
        matchingTasks.map { it.reference.delete().asDeferred() }.awaitAll()
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(TASK_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val TASK_COLLECTION = "tasks"
        private const val SAVE_TASK_TRACE = "saveTask"
        private const val UPDATE_TASK_TRACE = "updateTask"
    }
}