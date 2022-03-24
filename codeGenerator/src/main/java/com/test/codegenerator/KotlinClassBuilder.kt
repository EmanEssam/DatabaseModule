package com.test.codegenerator

class KotlinClassBuilder(
    className: String,
    packageName: String
) {

    private val contentTemplate = """
        package $packageName

   import androidx.room.Dao
   import androidx.room.Insert
   import androidx.room.OnConflictStrategy
   import androidx.room.Query
   import kotlinx.coroutines.flow.Flow

 
   @Dao
   interface ${className}Dao {

      
       @Insert(onConflict = OnConflictStrategy.REPLACE)
       suspend fun insert${className}s(${className}s: List<${className}>)

       
       @Query("DELETE FROM    $className   ")
       suspend fun deleteAll${className}s()

       /**
        * Fetches the $className from the [${className}.TABLE_NAME] table whose id is [${className}Id].
        * @param ${className}Id Unique ID of [${className}]
        * @return [Flow] of [${className}] from database table.
        */
       @Query("SELECT * FROM    $className    WHERE ID = :${className}Id")
       fun get${className}ById(${className}Id: Int): Flow<${className}>

       /**
        * Fetches all the ${className}s from the [${className}.TABLE_NAME] table.
        * @return [Flow]
        */
       @Query("SELECT * FROM    $className   ")
       fun getAll${className}s(): Flow<List<${className}>>
   }

    """.trimIndent()

    fun getContent(): String {

        return contentTemplate

    }


}