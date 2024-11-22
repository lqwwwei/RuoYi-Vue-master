package com.ruoyi.system.controller;

import java.io.InputStream;
import java.util.*;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Majorques;
import com.ruoyi.system.domain.Papers;
import com.ruoyi.system.domain.Tests;
import com.ruoyi.system.mapper.MajorquesMapper;
import com.ruoyi.system.mapper.PapersMapper;
import com.ruoyi.system.service.ITestsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Subjects;
import com.ruoyi.system.service.ISubjectsService;

import javax.security.auth.Subject;

/**
 * 试题管理Controller
 *
 * @author ruoyi
 * @date 2024-10-28
 */
@RestController
@RequestMapping("/system/subjects")
public class SubjectsController extends BaseController
{
    @Autowired
    private ISubjectsService subjectsService;
    @Autowired
    private PapersMapper papersMapper;
    @Autowired
    private MajorquesMapper Majorques;

    @Autowired
    private ITestsService testsService;
    /**
     * 导入试题
     */
    @Anonymous
//    @PreAuthorize("@ss.hasPermi('system:subjects:import')")
    @Log(title = "导入试题", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public AjaxResult importSubjects(@RequestParam("file") MultipartFile file) {

//导入大题表记录
        Majorques head=Majorques.selectMajorqueById(4);
        Majorques firstRecode=Majorques.selectMajorqueById(1);
        Majorques secondRecode=Majorques.selectMajorqueById(2);
        Majorques thirdRecode=Majorques.selectMajorqueById(3);

        try (InputStream inputStream = file.getInputStream()) {
            String fileName = file.getOriginalFilename();//获取文件名
            String title = fileName.substring(0, fileName.lastIndexOf("."));//去掉文件后缀当作title
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<Subjects> subjects = new ArrayList<>();

            //start 验证行数与列数
            int rowCount = sheet.getLastRowNum() + 1; // 行数从0开始计数
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();//列数
            if (rowCount < 34||colCount < 8) {
                throw new Exception("Excel 文件缺少内容");
            }
            if (rowCount > 34||colCount > 8) {
                throw new Exception("Excel 文件有多余内容");
            }
            //end 验证行数与列数


            Row headerRow = sheet.getRow(0);
            // start 比对标题行的每一个单元格与 head 记录的内容
            String[] expectedHeader = new String[]{
                    String.valueOf(head.getDicVal1()),
                    String.valueOf(head.getDicVal2()),
                    String.valueOf(head.getDicVal3()),
                    String.valueOf(head.getDicVal4()),
                    String.valueOf(head.getDicVal5()),
                    String.valueOf(head.getDicVal6()),
                    String.valueOf(head.getDicVal7()),
                    String.valueOf(head.getDicVal8())
            };
            for (int i = 0; i < expectedHeader.length; i++) {
                Cell cell = headerRow.getCell(i);
                if (cell == null || !expectedHeader[i].equals(getCellValueAsString(cell))) {
                    throw new Exception("标题行第 " + (i + 1) + " 列不匹配 ");
                }
            }
            // end 比对标题行的每一个单元格与 head 记录的内容

            int index = 0;//小题索引
            for (Row row : sheet) {
                if (row.getRowNum() == 0||row.getRowNum() == 1||row.getRowNum() == 12||row.getRowNum() == 23) { // 跳过标题行和大题行
                    continue;
                }
                //start 验证大题序号和标准答案内容
                if ( row.getRowNum() <= 12) {
                    if (!getCellValueAsString(row.getCell(0)).equals(firstRecode.getDicVal1())) {
                        throw new Exception("单选题的大题序号必须是一");
                    }
                    if (row.getRowNum() >= 1 && row.getRowNum() <= 10) {
                        String answer = getCellValueAsString(row.getCell(7));
                        if (!"A".equals(answer) && !"B".equals(answer) && !"C".equals(answer) && !"D".equals(answer)) {
                            throw new Exception("单选题的答案必须是A、B、C或D中的一个值");
                        }
                    }
                }

                if ( 13<=row.getRowNum()&&row.getRowNum() <= 23) {
                    if (!getCellValueAsString(row.getCell(0)).equals(secondRecode.getDicVal1())) {
                        throw new Exception("判断题的大题序号必须是二");
                    }
                    if (row.getRowNum() >= 14 && row.getRowNum() <= 23) {
                        String answer = getCellValueAsString(row.getCell(7));
                        if (!"T".equals(answer) && !"F".equals(answer)) {
                            throw new Exception("判断题的答案必须是T、F中的一个值");
                        }
                    }
                }
                if ( 24<=row.getRowNum()&&row.getRowNum() <= 34) {
                    if (!getCellValueAsString(row.getCell(0)).equals(thirdRecode.getDicVal1())) {
                        throw new Exception("多选题的大题序号必须是三");
                    }
                    if (row.getRowNum() >= 25 && row.getRowNum() <= 34) {
                        String answer = getCellValueAsString(row.getCell(7));
                        if (!answer.matches("[ABCD]{2,4}")) {
                            throw new Exception("多选题的答案必须是由A、B、C、D组成的任意组合，且每个字母最多出现一次");
                        }
                    }
                }
                //end 验证大题序号和标准答案内容


                //start 验证小题序号
                int order = getCellValueAsInt(row.getCell(1));
                if (order-1 != index) {
                    throw new Exception("小题序号必须是连续的，期望小题序号: " + (index+1)+ " 实际小题序号: " + order);
                }
                // 更新期望的小题序号
                index++;
                // 如果小题序号达到10，更新大题序号并重置小题序号
                if (index >= 10) {
                    index = 0;
                }
                //end 验证小题序号

                Subjects subject = new Subjects();
                subject.setPart(getCellValueAsString(row.getCell(0)));
                subject.setOrder(getCellValueAsInt(row.getCell(1)));
                subject.setContent(getCellValueAsString(row.getCell(2)));
                if(subject.getPart().equals(firstRecode.getDicVal1())){
                    subject.setType(0);
                }
                else if(subject.getPart().equals(secondRecode.getDicVal1())){
                    subject.setType(1);
                }
                else subject.setType(2);
                subject.setOptionA(getCellValueAsString(row.getCell(3)));
                subject.setOptionB(getCellValueAsString(row.getCell(4)));
                subject.setOptionC(getCellValueAsString(row.getCell(5)));
                subject.setOptionD(getCellValueAsString(row.getCell(6)));
                subject.setAnswer(getCellValueAsString(row.getCell(7)));
                subjects.add(subject);
            }

            Papers paper = new Papers();
            paper.setTitle(title); // 你可以从Excel或其他地方获取试卷标题
            paper.setTotal_score(100.00); // 你可以从Excel或其他地方获取总分
            paper.setCreatedAt(new Date());
            paper.setUpdatedAt(new Date());
            // 插入试卷记录
            int paperResult = papersMapper.insertPaper(paper);
            if (paperResult <= 0) {
                return AjaxResult.error("试卷记录插入失败");
            }

            Long paperId =paper.getId(); //
            boolean result = subjectsService.importSubjects(subjects, paperId);
            if (result) {
                Map<String, Object> data = new HashMap<>();
                data.put("paperId", paperId);
                return AjaxResult.success("试题导入成功",data);
            } else {
                return AjaxResult.error("试题导入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("文件解析或导入失败: " + e.getMessage());
        }
    }
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) {
            return 0;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                try {
                    return Integer.parseInt(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0; // 或者抛出异常，取决于你的业务需求
                }
            default:
                return 0;
        }
    }
//    @Anonymous
//    @GetMapping(value = "/{paperId}")
//    public AjaxResult getSubjectsByPaperId(@PathVariable("paperId") Long paperId) {
//        // 获取题目
//        List<Subjects> subjects = subjectsService.getSbujectsBypaperId(paperId);
//
//        return success(subjects);
//    }

    @Anonymous
    @GetMapping(value = "/{paperId}")
    public AjaxResult getSubjectsByType(@PathVariable("paperId") Long paperId,
                                        @RequestParam("subjectType") Integer subjectType) {
        // 根据试卷ID和试题类型获取题目
        List<Subjects> subjects = subjectsService.getSubjectsByPaperIdAndType(paperId, subjectType);

        return success(subjects);
    }



}