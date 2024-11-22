package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Subjects;
import com.ruoyi.system.domain.Testscores;

import com.ruoyi.system.mapper.SubjectsMapper;
import com.ruoyi.system.mapper.TestscoresMapper;
import com.ruoyi.system.service.ITestscoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class TestscoresServicelmpl implements ITestscoresService {
    @Autowired
    private TestscoresMapper testscoresMapper;

    @Autowired
    private SubjectsMapper subjectsMapper;
    @Override
    public void updateTests(Testscores testscores) {
        // 查找现有记录
        Testscores existingRecord = testscoresMapper.findRecode(testscores.getTestrecordsId(), testscores.getSubjectId());

        if (existingRecord != null) {
            // 更新现有记录
            testscores.setId(existingRecord.getId());
            testscores.setCreatedAt(new Date());
            testscores.setUpdatedAt(new Date());

            // 执行更新操作并返回受影响的行数
             testscoresMapper.updateTests(testscores);
        } else {
            // 创建新记录
            testscores.setCreatedAt(new Date());
            testscores.setUpdatedAt(new Date());

            // 执行插入操作并返回受影响的行数
             testscoresMapper.insert(testscores);
        }
    }

    @Override
    public Double calculateAndSaveScore(Testscores testscores) {
        // 根据subject_id和user_answer查找正确答案
        Subjects subject = subjectsMapper.findBySubjectId(testscores.getSubjectId());

        if (subject != null) {
            // 计算分数的逻辑
            double score = 0;
            System.out.println(666666666);
            System.out.println(subject.getType());
            if(subject.getType()==0) {
                System.out.println("该题是单选");
                if (subject.getAnswer().equals(testscores.getUserAnswer())) {
                    score = 3;  // 假设答对得3分
                } else {
                    score = 0;  // 假设答错得0分
                }
            }
            if(subject.getType()==1) {
                if (subject.getAnswer().equals(testscores.getUserAnswer())) {
                    score = 2;  // 假设答对得2分
                } else {
                    score = 0;  // 假设答错得0分
                }
            }
            else{
                String correctAnswer = subject.getAnswer();
                String userAnswer = testscores.getUserAnswer();
                if(correctAnswer.equals(userAnswer)){
                    score=5;
                }else {
                    // 初始化分数
                    // 每个位置的分数
                    double positionScore = 1.25;
                    // 将正确答案和用户答案转换为字符数组
                    char[] correctChars = correctAnswer.toCharArray();
                    char[] userChars = userAnswer.toCharArray();

                    // 获取较长的数组长度
//                    int maxLength = Math.max(correctChars.length, userChars.length);
//                    // 遍历每个位置
//                    for (int i = 0; i < maxLength; i++) {
//                        char correctChar = correctChars[i]; //
//                        char userChar = userChars[i]; //
//                        // 如果当前位置的选项正确
//                        if (correctChar == userChar) {
//                            score +=positionScore;
//                        }
//                    }
                    HashSet<Character> correctSet = new HashSet<>();
                    for (char c : correctAnswer.toCharArray()) {
                        correctSet.add(c); // 将正确答案中的每个字符添加到HashSet
                    }

                    for (char c : userAnswer.toCharArray()) {
                        if (correctSet.contains(c)) { // 检查用户答案中的字符是否在HashSet中
                            score += positionScore; // 如果存在，则增加分数
                            correctSet.remove(c); // 移除已经匹配过的字符，防止重复计分
                        }
                    }
                }
            }
            // 设置分数
            testscores.setScore(score);
            // 调用updateTests方法保存或更新记录

            updateTests(testscores);
            return score;
        } else {
            throw new RuntimeException("无法找到对应的试题信息");
        }
    }

    @Override
    public List<Testscores> getTestscoresById(Long testRecodeId){
        return testscoresMapper.getTestscoresById(testRecodeId);
    }
}
