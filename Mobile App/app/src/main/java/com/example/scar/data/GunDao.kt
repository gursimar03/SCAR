package com.example.scar.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.scar.ui.theme.Gun
import com.example.scar.ui.theme.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface GunDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Gun)

    @Update
    suspend fun update(item: Gun)

    @Delete
    suspend fun delete(item: Gun)

    @Query("SELECT * from guns WHERE gunID = :id")
    fun getItem(id: Int): Flow<Gun>

    @Query("SELECT * from guns ORDER BY gunID ASC")
    fun getAllItems(): Flow<List<Gun>>
}