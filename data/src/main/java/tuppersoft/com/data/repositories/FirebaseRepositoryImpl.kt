package tuppersoft.com.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import tuppersoft.com.data.mappers.toUser
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository
import javax.inject.Inject


class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    private lateinit var auth: FirebaseAuth

    override fun getUserLogin(): UserDto? {

        val user = FirebaseAuth.getInstance().currentUser?.toUser()

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("server/saving-data/adoptme1984")
        val usersRef = ref.child("users")

        usersRef.setValue(user)
        return user


    }
}