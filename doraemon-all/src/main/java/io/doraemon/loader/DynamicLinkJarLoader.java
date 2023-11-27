package main.java.io.doraemon.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.jna.NativeLibrary;

import com.sun.jna.Platform;


public class DynamicLinkJarLoader
{
    public static Path unpackLibrary (Class<?> clazz, String lib_name)
    {
        String user_dir = System.getProperty ("user.dir");
        File file = new File (user_dir, lib_name);
        File temp_dir = null;
        try
        {
            temp_dir = getTempDir ();
            file = new File (temp_dir, lib_name);
        } catch (IOException io)
        {
            io.printStackTrace ();
        }
        try
        {
            System.err.println ("Unpacking to: " + file.getAbsolutePath ().toString ());
            if (file.exists ())
                file.delete ();
            InputStream link = (clazz.getResourceAsStream (lib_name));
            Files.copy (link, file.getAbsoluteFile ().toPath ());
            NativeLibrary.addSearchPath(lib_name, temp_dir.getAbsolutePath());
            return file.getAbsoluteFile ().toPath ();
        } catch (Exception io)
        {
            io.printStackTrace ();
            System.err.println ("file: " + lib_name + " is not found in jar file");
            return null;
        }
    }

    static File getTempDir () throws IOException
    {
        File jna_tmp;
        String prop = System.getProperty ("jna.tmpdir");
        if (prop != null)
        {
            jna_tmp = new File (prop);
            jna_tmp.mkdirs ();
        } else
        {
            File tmp = new File (System.getProperty ("java.io.tmpdir"));
            if (Platform.isMac ())
            {
                // https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/FileSystemProgrammingGuide/MacOSXDirectories/MacOSXDirectories.html
                jna_tmp = new File (System.getProperty ("user.home"), "Library/Caches/JNA/temp");
            } else if (
                Platform.isLinux () || Platform.isSolaris () || Platform.isAIX () || Platform.isFreeBSD ()
                        || Platform.isNetBSD () || Platform.isOpenBSD () || Platform.iskFreeBSD ()
            )
            {
                // https://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html
                // The XDG_CACHE_DIR is expected to be per user
                String xdg_cache_environment = System.getenv ("XDG_CACHE_HOME");
                File xdg_cache_file;
                if (xdg_cache_environment == null || xdg_cache_environment.trim ().isEmpty ())
                {
                    xdg_cache_file = new File (System.getProperty ("user.home"), ".cache");
                } else
                {
                    xdg_cache_file = new File (xdg_cache_environment);
                }
                jna_tmp = new File (xdg_cache_file, "JNA/temp");
            } else
            {
                // Loading DLLs via System.load() under a directory with a unicode
                // name will fail on windows, so use a hash code of the user's
                // name in case the user's name contains non-ASCII characters
                jna_tmp = new File (tmp, "jna-" + System.getProperty ("user.name").hashCode ());
            }

            jna_tmp.mkdirs ();
            if (!jna_tmp.exists () || !jna_tmp.canWrite ())
            {
                jna_tmp = tmp;
            }
        }
        if (!jna_tmp.exists ())
        {
            throw new IOException ("JNA temporary directory '" + jna_tmp + "' does not exist");
        }
        if (!jna_tmp.canWrite ())
        {
            throw new IOException ("JNA temporary directory '" + jna_tmp + "' is not writable");
        }
        return jna_tmp;
    }
}
