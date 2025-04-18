package com.example.springbootspringai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBootSpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpringAiApplication.class, args);
    }


    /**
     * 创建 ChatClient 实例
     *
     * @param chatClientBuilder ChatClient 构建器
     * @return ChatClient 实例
     */
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.defaultSystem("""
                       ### Goal
            You are a SQL expert.
            Please help to generate a SQL query to answer the question. Your response should ONLY be based on the given context and follow the response guidelines and format instructions. 
                        """)
//                .defaultAdvisors(
//                        new MessageChatMemoryAdvisor(chatMemory), // CHAT MEMORY
//                        new QuestionAnswerAdvisor(vectorStore), // RAG,
//                        new SimpleLoggerAdvisor()) // RAG
//                .defaultFunctions("getBookingDetails", "changeBooking", "cancelBooking") // FUNCTION CALLING
                .build();
    }
}
