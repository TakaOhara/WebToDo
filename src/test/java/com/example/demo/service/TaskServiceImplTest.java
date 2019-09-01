package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.app.task.TaskForm;
import com.example.demo.entity.Task;

/**
 *
 */
@SpringJUnitConfig //ExtendWith　contentConfigration SpringBootでJunit5を使う
@SpringBootTest //毎回サーバ起動
@ActiveProfiles("unit")//application-unit.ymlのunitを対応（DBの設定を読み込む）
@DisplayName("TaskServiceImplの結合テスト")
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;

    @Test//order byがある場合は順序の確認することがある
    @DisplayName("全件検索のテスト")
    void testFindAllCheckCount() {
        List<Task> list = taskService.findAll();

        //Tasksテーブルに入っている3件が取得できているか確認
        assertEquals( 2, list.size());
    }
    
    @Test
    @DisplayName("タスクが取得できない場合のテスト")
    void testGetTaskFormReturnNull() {
        
        try {
        // サービスを実行
        	Optional<Task> taskO = taskService.getTask(0);
        } catch (EmptyResultDataAccessException e) {
        	assertEquals(e.getMessage(), "Incorrect result size: expected 1, actual 0");
        }
    }
    
    @Test
    @DisplayName("1件のタスクが取得できた場合のテスト")
    void testGetTaskFormReturnOne() {
        Optional<Task> task = taskService.getTask(1);
        
        assertEquals( "JUnitを学習", task.get().getTitle());
    }
    


}