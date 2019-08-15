/*
 * FileName: WrapperedResponse.java
 * Author:   guanshubang
 * Date:     2018/8/8 下午4:19
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      〈time>      <version>    <desc>
 * 修改人姓名    修改时间       版本号      描述
 */
package top.fqq.sms4.support;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 〈自定义输出〉
 * 〈功能详细描述〉
 *
 * @author guanshubang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class WrapperedResponse extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;
    private PrintWriter writer;

    public WrapperedResponse(HttpServletResponse resp) throws IOException {
        super(resp);
        buffer = new ByteArrayOutputStream();// 真正存储数据的流
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer,
                this.getCharacterEncoding()));
    }

    /** 重载父类获取outputstream的方法 */
    @Override
    public ServletOutputStream getOutputStream(){
        return out;
    }

    /** 重载父类获取writer的方法 */
    @Override
    public PrintWriter getWriter(){
        return writer;
    }

    /** 重载父类获取flushBuffer的方法 */
    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    /** 将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据 */
    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    /** 内部类，对ServletOutputStream进行包装 */
    private class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos;

        public WapperedOutputStream(ByteArrayOutputStream stream){
            bos = stream;
        }

        @Override
        public void write(int b){
            bos.write(b);
        }

        @Override
        public void write(byte[] b){
            bos.write(b, 0, b.length);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }
}