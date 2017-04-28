// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class UploadUtils {

    /**
     * 。
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings({"javadoc", "boxing"})
    public static List<String> uploadImages(final File[] img, final String[] imgFileName,
            final String imageType)
            throws FileNotFoundException, IOException {

        final List<String> imageNameList = new ArrayList<String>();

        String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("");
        path = path + "/upload/" + imageType + "/";
        for (int i = 0; i < imgFileName.length; i++) {

            String fileName = System.currentTimeMillis() + imgFileName[i].substring(imgFileName[i].lastIndexOf("."));
            final File fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            imageNameList.add("/upload/" + imageType + "/" + fileName);
            fileName = path + "/" + fileName;
            final File fileTmp = new File(fileName);
            int byteRead = 0;
            final FileInputStream fis = new FileInputStream(img[i]);
            final FileOutputStream fos = new FileOutputStream(fileTmp);
            final byte[] buffer = new byte[1024];
            while ((byteRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, byteRead);
            }
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(fos);

        }

        return imageNameList;
    }

}
