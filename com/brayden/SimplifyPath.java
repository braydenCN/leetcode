package com.brayden;

import static org.junit.Assert.*;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Test;

public class SimplifyPath {
    public String simplifyPath(String path) {
        if(path == null || path.length() == 0)
            return path;
        
        if(path.endsWith("/"))
            path = path.substring(0, path.length() - 1);
        
        String[] parts = path.split("/");
        
        Deque<String> pathList = new LinkedList<>();
        for(String part: parts)
            switch(part){
            case "": case ".": 
                break;
            case "..":
                if(!pathList.isEmpty())
                    pathList.pollLast();
                break;
            default:
                pathList.offerLast(part);
            }
        
        if(pathList.isEmpty())
            return "/";
        
        StringBuilder sb = new StringBuilder();
        for(String str: pathList){
            sb.append("/");
            sb.append(str);
        }
        return sb.toString();
    }
    
    @Test
    public void test(){
        assertEquals("/home", simplifyPath("/home"));
        assertEquals("/c", simplifyPath("/a/./b/../../c/"));
        assertEquals("/", simplifyPath("/../"));
        assertEquals("/home/foo", simplifyPath("/home//foo"));
    }
}
