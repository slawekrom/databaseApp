package com.example.sawek.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;
@Dao
public interface QuestionDAO {

    @Insert
    void insert(Question question);

    @Query("DELETE FROM QUESTION")
    void deleteAll();

    @Query("SELECT * FROM QUESTION c WHERE c.category LIKE :search")
    List<Question> searchQuestion(String search);

    @Delete
    void delete(Question question);

    @Query("SELECT * from QUESTION ORDER BY question ASC")
    LiveData<List<Question>> getAllQuestions();
    @Update
    void update(Question question);
}

