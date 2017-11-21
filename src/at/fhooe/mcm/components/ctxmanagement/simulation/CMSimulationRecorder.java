package at.fhooe.mcm.components.ctxmanagement.simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import at.fhooe.mcm.context.elements.ContextSituation;

public class CMSimulationRecorder {

	private int mIndex = 0;
	private String mDirectoryName;
	private String mFullPath;
	
	public CMSimulationRecorder () {
		Date date = new Date();
		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
		
		mDirectoryName = sdt.format(date);
		// Create Dir
		new File("logs/" + mDirectoryName + "/").mkdirs();
		mFullPath = "logs/" + mDirectoryName + "/";	
	}
	
	public void writeContextSituation(ContextSituation _cs) {
		Gson gson = new Gson();
		String json = gson.toJson(_cs);
		File file = new File(mFullPath + "_" + mIndex++ + ".cslog");
		try {
			if (file.createNewFile()) {
				FileWriter fw = new FileWriter(file);
				fw.write(json);
				fw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
