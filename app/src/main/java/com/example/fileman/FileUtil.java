package com.example.fileman;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class FileUtil {

   private static final String StorageDir = "/sdcard";
   private ArrayList<String> filePaths;

   //public called function to return all paths for files and directories
   public static void logPaths(){
       File directory = new File(StorageDir);
       FileUtil.walk(directory);
   }

   //public function to return an ArrayList of file paths
   public ArrayList<String> getAllFilePaths() throws Exception {
       filePaths = new ArrayList<>();
       File directory = new File(StorageDir);
       try{
           addAllFilePaths(directory);
       } catch (Exception ex){

           Log.d("Files", ex.getMessage());
           throw new Exception("Error: getAllPaths(File root) " + ex.getMessage());
       }

       return filePaths;
   }

    //recursive function to log all file paths and directories on the /sdcard
    private static void walk(File root) {

        File[] list = root.listFiles();

        for (File f : list) {
            if (f.isDirectory()) {
                Log.d("Files", "Dir: " + f.getAbsoluteFile());
                walk(f);
            }
            else {
                Log.d("Files", "File: " + f.getAbsoluteFile());
            }
        }
    }

    //recursive function to populate an ArrayList with all file paths
    private void addAllFilePaths(File root) throws Exception {

       try{

           File[] list = root.listFiles();
           for (File f : list) {
               if (f.isDirectory()) {
                   //Log.d("Files", "Dir: " + f.getAbsoluteFile());
                   addAllFilePaths(f);
               }
               else {
                   //Log.d("Files", "File: " + f.getAbsoluteFile());
                   filePaths.add(f.getAbsolutePath());
               }
           }

       }catch (Exception ex){
           Log.d("Files", ex.getMessage());
           throw new Exception("Error: addAllPaths(File root) " + ex.getMessage());

       }


    }//end getAllPaths

    public  ArrayList<String> getAllFilePathsExtensionDetails() throws Exception {
       ArrayList<String> filePathDetails = new ArrayList<>();
        filePaths = new ArrayList<>();
        File directory = new File(StorageDir);
        try{

            ArrayList<String> tempDirList = new ArrayList<>();
            addAllFilePaths(directory);

            //add filePaths into a temp list to use as a distinct count list for file extensions
            for(String p :filePaths){
                //do something here
                String[] sa = p.split("\\.");
                if(sa.length > 1 ){
                    Log.d("Files", sa[sa.length - 1]);
                    if(!tempDirList.contains(sa[sa.length - 1])){
                        tempDirList.add(sa[sa.length - 1]);
                    }
                }

            }//end for

            for(String fileExt : tempDirList){
                int extCount = 0;
                for(String ext : filePaths) {
                    String[] sa = ext.split("\\.");
                    if (sa.length > 1) {
                        if (sa[sa.length - 1].equals(fileExt)) {
                            extCount++;
                        }
                    }
                }

                filePathDetails.add(("." + fileExt + " total found: " + extCount));


            }

        } catch (Exception ex){

            Log.d("Files", ex.getMessage());
            throw new Exception("Error: getAllPaths(File root) " + ex.getMessage());
        }

        return filePathDetails;

    }




}
