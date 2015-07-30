package com.sunkai.demo;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by sunkai on 7/29/15.
 */
public class UploadFile extends ActionSupport {
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;

    private String filePath;
    private String md5;
    private String manifestPackage;
    private String launchActivityPackage;
    private String shellFilePath;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getManifestPackage() {
        return manifestPackage;
    }

    public void setManifestPackage(String manifestPackage) {
        this.manifestPackage = manifestPackage;
    }

    public String getLaunchActivityPackage() {
        return launchActivityPackage;
    }

    public void setLaunchActivityPackage(String launchActivityPackage) {
        this.launchActivityPackage = launchActivityPackage;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String execute() {
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            setFilePath(filePath + fileUploadFileName);
            File fileToCreate = new File(filePath, this.fileUploadFileName);

            FileUtils.copyFile(this.fileUpload, fileToCreate);
            checkApk(request);

            fileToCreate.delete();
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());

            return INPUT;
        }
        return SUCCESS;
    }

    private void checkApk(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String shellPath = servletContext.getRealPath("/") + "WEB-INF/checkApk.sh";
        System.out.println(shellPath + "  " + getFilePath());
        setShellFilePath(shellPath);
        try {
            Runtime.getRuntime().exec("chmod 777 " + shellPath);
            Process process = Runtime.getRuntime().exec(shellPath + " " + getFilePath());
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println("line: "+line);
                if (line.indexOf("MD5") >= 0) {
                    setMd5(line.substring(5));
                } else if (line.indexOf("Mani") >= 0) {
                    setManifestPackage(line.substring(17));
                } else if (line.indexOf("Laun") >= 0) {
                    setLaunchActivityPackage(line.substring(23));
                }
            }
            if (null != input) {
                input.close();
            }

            if (null != ir) {
                ir.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getShellFilePath() {
        return shellFilePath;
    }

    public void setShellFilePath(String shellFilePath) {
        this.shellFilePath = shellFilePath;
    }
}
