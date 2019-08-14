package top.fqq.sms4.utils;

/**
 * @Auther: fqq
 * @Date: 2019/8/14 17:11
 * @Description:
 */
public class SM4_Context
{
    public int mode;

    public int[] sk;

    public boolean isPadding;

    public SM4_Context()
    {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new int[32];
    }
}
