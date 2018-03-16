package com.example.demo.actuator.customendpoints;

import org.springframework.stereotype.Component;

/**
 * This is Customized end point for Spring boot actuator
 * 
 * @author 
 *
 */
@Component
public class CustomEndpoint {

//implements Endpoint<List<String>> {
//     
//    @Override
//    public String getId() {
//        return "customEndpoint";
//    }
// 
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
// 
//    @Override
//    public boolean isSensitive() {
//        return true;
//    }
// 
//    @Override
//    public List<String> invoke() {
//        // Custom logic to build the output
//        List<String> messages = new ArrayList<String>();
//        messages.add("This is message 1");
//        messages.add("This is message 2");
//        return messages;
//    }
}
