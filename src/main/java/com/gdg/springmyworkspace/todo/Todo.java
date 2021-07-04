package com.gdg.springmyworkspace.todo;

//Aggregation Root ��ü
//������ ������ ��ü�� �����ϰ� �ִ� ��ü
//���� �Ѹ� ��ü

//��) order �ֹ����� - orderDetail
//
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

//@Entity = �����ͺ��̽��� ���̺�� ������(mapping)
//ORM�̶�� �Ѵ�. Object relational Mapping : ��ü�� ���̺��� �����ϴ� ��
//class�� ���̺��� pascal-case -> snake-case�� ����
//StudendInfo -> sutdent_info

// �ʵ�� �÷��� camel-case -> snake-case�� ����
// CreatedTime -> created_time

//�ڵ� ���迡 ���� �����ͺ��̽��� ��������� �� : auto-migration

@Data
@Entity // ��ü - �����ͺ��̽��� ���ڵ��̴�. �ϳ��� ���� ����
public class Todo {

	// @Id -> ���̺��� primary key
	// @GeneratedValue : �ʵ尪�� �����ϴ� ����� �����ϴ� �� / IDENTITY : �����ͺ��̽��� �ڵ� �������� ����Ѵٴ� �ǹ�
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String memo;
	private Long CreatedTime;

	@OneToMany
	// ������ �÷��� �����Ѵ�.
	@JoinColumn(name = "todoId")
	private List<TodoComment> comments;
}
