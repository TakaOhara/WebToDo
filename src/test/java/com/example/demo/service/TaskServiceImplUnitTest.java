package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.app.task.TaskForm;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskDao;

@ExtendWith(MockitoExtension.class)//P385 RunWithとの違いJunit4
@DisplayName("TaskServiceImplの単体テスト")
class TaskServiceImplUnitTest {
	

	
    @Mock // モック(stub)クラス ダミーオブジェクト
    private TaskDao dao;

    @InjectMocks // テスト対象クラス　モックを探す newする
    private TaskServiceImpl taskServiceImpl;
    
    @Test // テストケース
    @DisplayName("テーブルtaskの全件取得で0件の場合のテスト")
        // テスト名
    void testFindAllReturnEmptyList() {
    	
    	//モックから返すListに2つのTaskオブジェクトをセット
    	List<Task> list = new ArrayList<>();
    	 	
        // モッククラスのI/Oをセット（findAll()の型と異なる戻り値はNG）
        when(dao.findAll()).thenReturn(list);

        // サービスを実行
        List<Task> actualList= taskServiceImpl.findAll();

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findAll();

        // 戻り値の検査(expected, actual)
        assertEquals(0, actualList.size());
        
    }
    
    @Test // テストケース
    @DisplayName("テーブルTaskの全件取得で2件の場合のテスト")
        // テスト名
    void testFindAllReturnList() {
    	
    	//モックから返すListに2つのTaskオブジェクトをセット
    	List<Task> list = new ArrayList<>();
    	Task task1 = new Task();
    	Task task2 = new Task();
    	list.add(task1);
    	list.add(task2);
    	
    	
        // モッククラスのI/Oをセット（findAll()の型と異なる戻り値はNG）
        when(dao.findAll()).thenReturn(list);

        // サービスを実行
        List<Task> actualList= taskServiceImpl.findAll();

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findAll();

        // 戻り値の検査(expected, actual)
        assertEquals(2, actualList.size());
        
    }

    @Test // テストケース
    @DisplayName("タスクが取得できない場合のテスト")
        // テスト名
    void testGetTaskFormThrowException() {
    	
        // モッククラスのI/Oをセット
        when(dao.findById(0)).thenThrow(new EmptyResultDataAccessException(1));


        // モックの指定メソッドの実行回数を検査
        //verify(dao, times(1)).findById(0);
        
        try {
        // サービスを実行
        	Optional<Task> taskO = taskServiceImpl.getTask(0);
        } catch (EmptyResultDataAccessException e) {
        	assertEquals(e.getMessage(), "Incorrect result size: expected 1, actual 0");
        }
        
    }
    
    @Test // テストケース
    @DisplayName("タスクを1件取得した場合のテスト")
        // テスト名
    void testGetTaskFormReturnOne() {
    	
    	//TaskFormをデフォルト値でインスタンス化
    	Task task = new Task();
    	Optional<Task> form  = Optional.ofNullable(task);
        // モッククラスのI/Oをセット
        when(dao.findById(1)).thenReturn(form);

        // サービスを実行
        Optional<Task> taskActual = taskServiceImpl.getTask(1);

        // モックの指定メソッドの実行回数を検査
        verify(dao, times(1)).findById(1);

        // 戻り値の検査(1件インスタンスが戻ってきていることを検査する方法は？
        //これでよいか？
        //assertTrue( taskActual.get() instanceof TaskForm );
        assertTrue( taskActual.isPresent());
        
    }
    
    @Test // テストケース　単体テストではデータベースの例外は考えない
    @DisplayName("存在しないidの場合メソッドが実行されないことを確認するテスト")
        // テスト名
    void throwNotFoundException() {
    	
        // モッククラスのI/Oをセット
        when(dao.deleteById(0)).thenReturn(0);

        try {
        // サービスを実行
        	taskServiceImpl.deleteById(0);
        } catch (TaskNotFoundException e) {
        	assertEquals(e.getMessage(), "指定されたタスクが存在しません。");
        }
    }
    
    
}