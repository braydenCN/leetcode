package com.brayden;

/**
 * 
Read N Characters Given Read4
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 
if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.
 * 
 */
public class ReadNCharactersGivenRead4 {
    private int read4(char[] buf){
        return 0;
    }
    
    int read(char[] buf, int n) {
        if(n <= 0 || buf.length < n)
            return 0;
        
        char[] localBuf = new char[4];
        int count = 0, num = 0;
        while(n >= 0){
            num = read4(localBuf);
            System.arraycopy(localBuf, 0, buf, count, num);
            count += num;
            if(num < 4)
                return count;
            n -= num;
        }
        
        return count;
    }
}
