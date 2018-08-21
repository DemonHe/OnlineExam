package test.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import test.domain.Selection;

public interface SelectionRepository extends JpaRepository<Selection, Integer> {

	public ArrayList<Selection> findByQid(int qid);

	public Selection findById(int id);
}
