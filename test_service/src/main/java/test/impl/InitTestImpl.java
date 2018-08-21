package test.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.domain.AnswerRecord;
import test.domain.Question;
import test.domain.ScorePerQuestion;
import test.domain.Selection;
import test.repository.AnswerRecordRepositiry;
import test.repository.QuestionRepository;
import test.repository.ScorePerQuestionRepository;
import test.repository.SelectionRepository;
import test.repository.TestRepository;
import test.service.InitTestService;
import test.vo.SelectionVO;
import test.vo.TestVO;

@Service
public class InitTestImpl implements InitTestService {

	@Autowired
	TestRepository testRepo;
	@Autowired
	ScorePerQuestionRepository scoreRepo;
	@Autowired
	QuestionRepository questionRepo;
	@Autowired
	SelectionRepository selectionRepo;
	@Autowired
	AnswerRecordRepositiry answerRepo;

	@Override
	public ArrayList<TestVO> getTest(String studentId, int testId) {
		List<AnswerRecord> list = answerRepo.findByTestIDAndSid(testId, studentId);
		ArrayList<TestVO> testList = new ArrayList<TestVO>();
		ArrayList<SelectionVO> selectionList = new ArrayList<SelectionVO>();
		ArrayList<SelectionVO> realSelectionList = new ArrayList<SelectionVO>();

		SelectionVO s;
		TestVO t;
		int tmp = -1;
		int size = list.size() - 1;
		Question question = null;

		for (AnswerRecord test : list) {

			int qid = test.getQid();
			if (tmp == -1)
				tmp = qid;
			int select = test.getSelectionID();

			if (qid != tmp) {
				question = questionRepo.findById(tmp);

				realSelectionList = selectionList;
				t = new TestVO(testId, studentId, tmp, question.getContent(), realSelectionList, question.getScore());
				testList.add(t);
				tmp = qid;

				selectionList=new ArrayList<SelectionVO>();
			}

			Selection selection = selectionRepo.findById(select);
			s = new SelectionVO(selection.getId(), selection.getContent(), test.getOptOrder());
			selectionList.add(s);

			if (size == 0) {
				question = questionRepo.findById(qid);

				realSelectionList = selectionList;
				t = new TestVO(testId, studentId, qid, question.getContent(), realSelectionList, question.getScore());
				testList.add(t);
			}

			size--;
		}
		return testList;
	}

	@Override
	public void initTest(String studentId, int testId) {
//		System.out.println("tid:"+t);
//		System.out.println(answerRepo.count());
		// 如果已经生成试卷
		if (answerRepo.count(testId,studentId) > 0)
			return;
		// 存储到AnswerRecord中
		AnswerRecord answer;

		// 中间值
		ArrayList<Double> checkRepeatScore = new ArrayList<Double>(20);
		ArrayList<Integer> checkRepeatQuestion = new ArrayList<Integer>();
		ArrayList<ArrayList<Question>> list = new ArrayList<ArrayList<Question>>(20);
		ArrayList<Question> questionList = new ArrayList<Question>();
		ArrayList<Selection> selectTmpList = new ArrayList<Selection>(10);

		// 随机数
		Random random = new Random();

		List<ScorePerQuestion> scoreList = scoreRepo.findByTestID(testId);
		int cid = testRepo.findCourse(testId);

		for (ScorePerQuestion score : scoreList) {
			// 清除所有数据
			selectTmpList.clear();

			double mark = score.getScore();System.out.println("mark:"+mark);
			if (!checkRepeatScore.contains(mark)) {
				checkRepeatScore.add(mark);
				questionList = questionRepo.findByScoreAndCourseID(mark, cid);
				list.add(questionList);
			}
			// 分数对应的题目列表选定
			int index = checkRepeatScore.indexOf(mark);
			questionList = list.get(index);
			int qindex = random.nextInt(questionList.size());System.out.println("qindex0:"+qindex);
			int qid = questionList.get(qindex).getId();System.out.println("qid:"+qid);
			while (checkRepeatQuestion.contains(qid)) {
				qindex = random.nextInt(questionList.size());System.out.println("qindex:"+qindex);
				qid = questionList.get(qindex).getId();
			}

			// qid选定
			checkRepeatQuestion.add(qid);

			// 随机组织选项顺序
			selectTmpList = selectionRepo.findByQid(qid);
			int size = selectTmpList.size();
			int count = size;
			while (count > 0) {
				answer = new AnswerRecord();
				answer.setQid(qid);
				answer.setTestID(testId);
				answer.setSid(studentId);

				int sid = random.nextInt(count);
				// 选项顺序
				String optOrder = String.valueOf((char) (65 + size - count));

				// selection确定，保存题目
				answer.setSelectionID(selectTmpList.get(sid).getId());
				answer.setOptOrder(optOrder);
				answer.setIsSelected(0);
				answerRepo.save(answer);
				selectTmpList.remove(sid);

				count--;
			}
		}

		checkRepeatScore = null;
		checkRepeatQuestion = null;
		list = null;
		questionList = null;
		selectTmpList = null;
	}
}
