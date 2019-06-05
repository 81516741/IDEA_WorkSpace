import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


public class HdfsClient {
    static FileSystem fs = null;
    @Test
    public  void testUpload() throws Exception {
        configFS();
        fs.copyFromLocalFile(new Path("/Users/lingda/Desktop/笔记.xlsx"),new Path("/dd/"));
    }

    @Test
    public void testDownload() throws Exception {
        configFS();
        fs.copyToLocalFile(new Path("/笔记.xlsx"),new Path("/Users/lingda/Desktop/笔记11.xlsx"));
    }

    @Test
    public void testMove() throws Exception {
        configFS();
        fs.rename(new Path("/nimeide.gz"),new Path("/haode/"));
    }

    @Test
    public void testMkdir() throws Exception {
        configFS();
        fs.mkdirs(new Path("/nimeide/haoba"));
    }

    @Test
    public void testDelete() throws Exception {
        configFS();
        fs.delete(new Path("/nimeide"),true);
    }

    @Test
    public void testls() throws Exception {//带递归,获取文件
        configFS();
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fs.listFiles(new Path("/"), true);
        while (locatedFileStatusRemoteIterator.hasNext()) {
            FileStatus next = locatedFileStatusRemoteIterator.next();
            System.out.println("size:" + next.getBlockSize());
            System.out.println("rep；" + next.getReplication());
            System.out.println("len：" + next.getLen());
            System.out.println("path：" + next.getPath());
            System.out.println("块信息：" + Arrays.toString(((LocatedFileStatus) next).getBlockLocations()));
        }
    }

    @Test
    public void testls1() throws Exception {//获取文件 和 文件夹
        configFS();
        FileSystem fs = HdfsClient.fs;
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus status:fileStatuses){
            System.out.println(status.isDirectory() ? "这是个文件夹" : "这是个文件");
            System.out.println("size:" + status.getBlockSize());
            System.out.println("rep；" + status.getReplication());
            System.out.println("len：" + status.getLen());
            System.out.println("path：" + status.getPath());
        }
    }

    //初始化
    public void configFS() throws Exception {
        Configuration config = new Configuration();
        config.set("dfs.blocksize","50m");
        config.set("dfs.replication","3");
        fs = FileSystem.get(new URI("hdfs://hadoop1:9000/"), config, "root");
    }

}
