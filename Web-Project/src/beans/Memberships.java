package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

public class Memberships {
	private HashMap<String, Membership> memberships = new HashMap<String, Membership>();
	private ArrayList<Membership> membershipList = new ArrayList<Membership>();

	public Memberships() {
		this("static");
	}

	public Memberships(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/memberships.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readMemberships(in);
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
	
	private void readMemberships(BufferedReader in) {
		String line, id="", customer="";
		double price = 0 ;
		int visits = 0;
		LocalDate payDate = null;
		LocalDateTime validDateTime = null;
		MembershipType type =MembershipType.MONTHLY;
		//Location location = new Location();
		boolean active = false;
		boolean gotPoints = false;
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					type = MembershipType.valueOf(st.nextToken().trim());
					payDate = LocalDate.parse(st.nextToken().trim());
					validDateTime = LocalDateTime.parse(st.nextToken().trim());
					price = Double.valueOf(st.nextToken().trim());
					customer = st.nextToken().trim();
					LocalDateTime current = LocalDateTime.now();
					if(validDateTime.isAfter(current)) {
						active = true;
					} else 
						active = false;
					//active = Boolean.valueOf(st.nextToken().trim());
					visits = Integer.valueOf(st.nextToken().trim());
					gotPoints = Boolean.valueOf(st.nextToken().trim());
					Users us = new Users();
					if(validDateTime.isBefore(current) && gotPoints==false) {
						us.updatePoints(customer, new Membership(id, type, payDate, validDateTime, price, customer, active, visits, gotPoints));
						gotPoints = true;
					}
					if(active == true) {
						us.setMembership(customer, new Membership(id, type, payDate, validDateTime, price, customer, active, visits, gotPoints));
						User u = us.getUser(customer); //OVO JE BILO SAMO TESTIRANJE JESU UCITANI
						System.out.println(u.getMembership().getId());
					}
				}
				Membership membership = new Membership(id, type, payDate, validDateTime, price, customer, active, visits, gotPoints);
				memberships.put(id, membership);
				membershipList.add(membership);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
public void saveData(){
		
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("static/memberships.txt", "UTF-8");
			
			for (Membership membership : memberships.values()) {
				writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s", 
						membership.getId(), membership.getType(), membership.getPayDate(), membership.getValidTime(), membership.getPrice(), membership.getCustomer(), membership.getDailyVisit(), membership.isGotPoints()));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public Collection<Membership> values() {
		return memberships.values();
	}

	public Collection<Membership> getValues() {
		return memberships.values();
	}

	public Membership getMembership(String id) {
		return memberships.get(id);
	}

	public ArrayList<Membership> getMembershipList() {
		return membershipList;
	}
	
	public void addMembership(Membership m) {
		if (memberships.containsKey(m.getId())) {
			return;
		} else {
			this.memberships.put(m.getId(), m);
			saveData();
		}
	} 
	public void ChangeToInactive(Membership m) {
		m.setValidTime(LocalDateTime.now());
		m.setActive(false);
		this.memberships.put(m.getId(), m);
		saveData();
	}
	public void ChangeToGotPoints(Membership m) {
		m.setGotPoints(true);
		this.memberships.put(m.getId(), m);
		saveData();
	}
}
