package com.example.springbootactiviti7;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootActiviti7ApplicationTests {

    /**
     * 测试工作流引擎自动创建表
     */
    @Test
    public void createProcessEngine() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }

    /**
     * 测试流程部署
     */
    @Test
    public void testDeployment() {
        // 1.创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.使用ProcessEngine对象创建操作数据库服务RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3.使用RepositoryService对象进行流程部署,定义一个流程名字，把bpmn文件和png文件部署到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请") // 流程名字
                .addClasspathResource("bpmn/evection.bpmn20.xml") // 资源文件   bpmn配置文件
                .addClasspathResource("png/evection.png") // 资源文件   png流程图
                .disableSchemaValidation()   //  禁止校验文件
                .deploy();// 部署

        // 4.输出部署信息
        System.out.println("部署ID:" + deploy.getId());
        System.out.println("部署名称:" + deploy.getName());
    }


    /**
     * 测试启动流程实例
     */
    @Test
    public void testStartProcess() {
        // 1. 创建 ProcessEngine 对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取runtimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3. 根据流程定义的id启动流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection");
        // 4. 输出信息
        System.out.println("流程定义ID:" + instance.getProcessDefinitionId());
        System.out.println("流程实例ID:" + instance.getId());
        System.out.println("当前活动ID:" + instance.getActivityId());

    }

    // 根据任务id完成个人任务
    @Test
    public void testCompletTask() {
        // 1 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2 获取service
        TaskService taskService = processEngine.getTaskService();
        // 3 根据任务id查询任务
        taskService.complete("090847d6-9b49-11ef-b82a-28cdc46f3daf");
    }

    /**
     * 获取任务列表
     */
    @Test
    public void testSelectTodoTaskList() {
        //任务负责人
        String assignee = "lisi";
        //创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取TaskService
        TaskService taskService = processEngine.getTaskService();
        //获取任务集合
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee(assignee)
                .list();
        //遍历任务列表
        for (Task task : taskList) {
            System.out.println("流程定义id = " + task.getProcessDefinitionId());
            System.out.println("流程实例id = " + task.getProcessInstanceId());
            System.out.println("任务id = " + task.getId());
            System.out.println("任务名称 = " + task.getName());
        }
    }

    // 根据任务id完成个人任务
    @Test
    public void testCompletTaskById() {
        // 1 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2 获取service
        TaskService taskService = processEngine.getTaskService();
        // 3 获取lisi在myEvection中对应的任务 我们已知只有一个任务 可使用singleResult获取一个结果集
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection") // 流程的key
                .taskAssignee("lisi")   // 要查询的负责人
                .singleResult();// 因为可能有多个待办  所以是一个集合

        System.out.println("流程实例ID:" + task.getProcessInstanceId());
        System.out.println("任务ID:" + task.getId());
        System.out.println("任务负责人:" + task.getAssignee());
        System.out.println("任务名称:" + task.getName());

        // 4 获取jerry的完成任务
        taskService.complete(task.getId());
    }

    // 根据任务id完成个人任务
    @Test
    public void testCompletTask1() {
        // 1 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2 获取service
        TaskService taskService = processEngine.getTaskService();
        Task task = null;

        // 3 获取zhangsan在myEvection中对应的任务 我们已知只有一个任务 可使用singleResult获取一个结果集
//         task = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection") // 流程的key
//                .taskAssignee("zhangsan")   // 要查询的负责人
//                .singleResult();// 因为可能有多个待办  所以是一个集合
//        taskService.complete(task.getId());

        task = taskService.createTaskQuery()
                .processDefinitionKey("myEvection") // 流程的key
                .taskAssignee("lisi")   // 要查询的负责人
                .singleResult();// 因为可能有多个待办  所以是一个集合
        taskService.complete(task.getId());


        task = taskService.createTaskQuery()
                .processDefinitionKey("myEvection") // 流程的key
                .taskAssignee("wangwu")   // 要查询的负责人
                .singleResult();// 因为可能有多个待办  所以是一个集合
        taskService.complete(task.getId());


        task = taskService.createTaskQuery()
                .processDefinitionKey("myEvection") // 流程的key
                .taskAssignee("zhaolou")   // 要查询的负责人
                .singleResult();// 因为可能有多个待办  所以是一个集合
        taskService.complete(task.getId());


    }
}
