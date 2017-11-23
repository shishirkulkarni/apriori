package edu.sjsu.cs255.structures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

//import org._3pq.jgrapht.Graph;
//import org._3pq.jgrapht.graph.SimpleGraph;
import com.google.common.graph.*;

import edu.sjsu.cs255.config.Config;
import edu.sjsu.cs255.exceptions.InvalidFormatException;

public class NetworkGraph {
	private MutableGraph<Integer> g;
	
	public NetworkGraph() {
		g = GraphBuilder.undirected().build();
	}
	
	public void loadFromFile(File f) throws IOException {
		//Clean any previous graph
		g = GraphBuilder.undirected().build();
		
		
		if(f == null || !f.exists()) {
			throw new FileNotFoundException("File not found!!");
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String s;
		while((s = reader.readLine()) != null) {
			if(!isValid(s))
				throw new InvalidFormatException("Line format invalid!!!");
			putIntoGraph(s);
		}
	
	}
	
	private boolean isValid(String line) {
		return Pattern.matches(Config.patternFormat, line);
	}
	
	private void putIntoGraph(String s) {
		String[] fields = s.split(":");
		int srcVertex = Integer.parseInt(fields[0].trim());
		g.addNode(srcVertex);
		String[] targetVertices = fields[1].split(",");
		for(String target: targetVertices) {
			int targetVertex = Integer.parseInt(target.trim());
			g.addNode(targetVertex);
			g.putEdge(srcVertex, targetVertex);
		}
	}
	
	public String toString() {
		return g.toString();
	}
	
	public Set<Integer> getFriends(int personId) {
		return g.adjacentNodes(personId);
	}
	
	public Set<Integer> nodes() {
		return g.nodes();
	}
	
	
}
