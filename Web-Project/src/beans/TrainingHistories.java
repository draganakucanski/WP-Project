package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class TrainingHistories {

	private HashMap<Integer, TrainingHistory> histories = new HashMap<Integer, TrainingHistory>();
	private ArrayList<TrainingHistory> historyList = new ArrayList<TrainingHistory>();

	public TrainingHistories() {
		this("static");
	}

	public TrainingHistories(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/trainingHistories.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readHistories(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		
	}
	
	private void readHistories(BufferedReader in) {
		int id = 0;
		LocalDateTime dateTime= null;
		String line, trainingName = "", customerUsername="", trainerUsername="";
		Training training = null;
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.valueOf(st.nextToken().trim());
					dateTime = LocalDateTime.parse(st.nextToken().trim());
					trainingName = st.nextToken().trim();
					customerUsername = st.nextToken().trim();
					trainerUsername = st.nextToken().trim();
					Trainings t = new Trainings();
					training  = t.getTraining(trainingName);
					Users u = new Users();
					u.addTrainingHistory(trainerUsername, new TrainingHistory(id, dateTime, training, customerUsername, trainerUsername));
				}
				TrainingHistory history = new TrainingHistory(id, dateTime, training, customerUsername, trainerUsername);
				histories.put(id, history);
				historyList.add(history);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
public void saveData(){
		
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("static/trainingHistories.txt", "UTF-8");
			
			for (TrainingHistory history : histories.values()) {
				writer.println(String.format("%s;%s;%s;%s;%s", history.getId(), history.getDateTime(),history.getTraining().getName(), history.getCustomer(), history.getTrainer()
						));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public Collection<TrainingHistory> values() {
		return histories.values();
	}

	public Collection<TrainingHistory> getValues() {
		return histories.values();
	}

	public TrainingHistory getHisotry(int id) {
		return histories.get(id);
	}

	public ArrayList<TrainingHistory> getTrainingList() {
		return historyList;
	}
	public void addTrainingHistory(TrainingHistory training) {
	  		int id = GenerateNewID();
	  		training.setId(id);
			System.out.println(training.getId());
			this.histories.put(training.getId(), training);
			saveData();
	}
	public int GenerateNewID() 
    {
        int max = 0;
        if (histories.size() != 0)
            for(TrainingHistory history: histories.values())
                if (max < history.getId())
                    max = history.getId();
        return max + 1;
    }
	public void Edit(TrainingHistory th) {
		this.histories.put(th.getId(), th);
		saveData();
	}
}
