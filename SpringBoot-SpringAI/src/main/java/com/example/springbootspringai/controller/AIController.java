package com.example.springbootspringai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.desktop.QuitEvent;
import java.lang.invoke.VarHandle;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * ClassName: AIController
 * Package: com.example.springbootspringai.controller
 * Description:
 *
 * @Author ms
 * @Create 2025/1/16 11:19
 * @Version 1.0
 */
@RestController
@RequestMapping
public class AIController {

    private final ChatClient chatClient;

    public AIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/ai")
    public String generation(@RequestParam(value = "userInput", defaultValue = "Tell me a joke") String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .call()

                .content();
    }

    /**
     * ChatResponse
     * @param userInput
     * @return
     */
    @GetMapping( "/ai/tool")
    public String generationTool(@RequestParam(value = "userInput", defaultValue = "Tell me a joke") String userInput) {
        ChatResponse chatResponse = this.chatClient.prompt()
                .user(userInput)
                .call()
                .chatResponse();

        AssistantMessage assistantMessage = chatResponse.getResult().getOutput();
//        return null;
        return assistantMessage.getContent();
    }

    /**
     * 动态过滤表达式
     * @param userInput
     * @return
     */
    @GetMapping("/ai/tool2")
    public String  generationTool2(@RequestParam(value = "userInput", defaultValue = "Tell me a joke") String userInput) {
       return this.chatClient.prompt().user(userInput)
               .advisors(a ->a.param(QuestionAnswerAdvisor.FILTER_EXPRESSION, "contains(\"joke\")"))
               .call()
               .content();
    }

    @GetMapping("/ai/tool3")
    public Flux<String> chat(String chatId, String userMessageContent) {
        return this.chatClient.prompt()
                .user(userMessageContent)
                .advisors(a -> a
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .stream().content();
    }


}
