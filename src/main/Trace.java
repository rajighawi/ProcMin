package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Trace {

	String trace;
	int freq;
	
	public Trace(String trace) {
		super();
		this.trace = trace;
		this.freq = 1;
	}
	
	public Trace(String trace, int freq) {
		super();
		this.trace = trace;
		this.freq = freq;
	}
	
	public String getTrace() {
		return trace;
	}
	
	public int getFreq() {
		return freq;
	}

	@Override
	public String toString() {
		return "[" + trace + "] @" + freq;
	}
	
	// ----------------------------------------------------------------
	
	public static ArrayList<Trace> parse(String s){
		ArrayList<Trace> traces = new ArrayList<Trace>();
		Scanner sc = new Scanner(s);
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			Scanner sc2 = new Scanner(line);
			String traceS = null;
			int freq = 1;
			if(sc2.hasNext()){
				traceS = sc2.next();
				if(sc2.hasNextInt()) freq = sc2.nextInt();
			}
			if(traceS!=null){
				Trace trace = new Trace(traceS, freq);
				traces.add(trace);
			}
			sc2.close();
		}
		sc.close();
		return traces;
	}
	
	public static ArrayList<Trace> parse(String[] s){
		ArrayList<Trace> traces = new ArrayList<Trace>();
		for (int i = 0; i < s.length; i++) {
			traces.add(new Trace(s[i]));			
		}
		return traces;
	}
	
	
}
