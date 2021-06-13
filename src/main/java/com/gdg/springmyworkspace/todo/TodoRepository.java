package com.gdg.springmyworkspace.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//�� interface���ٰ� repository��� �����س���?
//Ȯ���Ѱ� @Repository ����׼��� �پ������� �� �������̽��� ������ ����Ŭ������ �����Ѵ�.
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
