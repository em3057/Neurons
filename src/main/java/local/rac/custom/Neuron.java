package local.rac.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Neuron implements Iterable<Axon> {
	final private int indexInBrain ;
	
	private List<Axon> inputs ;
	
	// Things that can be adjusted during learning
	private double membraneTransmissionFactor ;

	// Immediate attributes as a result of stimulii
	private double futurePotential ;
	private double [] currentPotential ;	
	
	private int clock ;
	
	public Neuron( int indexInBrain ) {
		this.indexInBrain = indexInBrain ;
		this.clock = 0 ;
		
		this.inputs = new ArrayList<Axon>() ;
		
		// 		Other Constants
		this.membraneTransmissionFactor = 0.75 ;	// input weight

		// instance data
		this.currentPotential = new double[100] ;		// starting potentials
		this.futurePotential = 0 ;		
	}
	
	// Moves the future potential into the current potential.
	// this is done so that all outputs are updated simultaneously, all input 
	// during a 'clock' is based on T0 before T1 is established 
	public void lockOutput() {
		clock++ ;
		if( clock>=currentPotential.length ) clock=0 ; 
		currentPotential[clock] = futurePotential ;
	}
	
	
	// Based on all current inputs at T0 - set the T1 output value of the neuron
	public void clock() {		
		futurePotential = 0.0 ;
		for( Axon axon : this ) {
			futurePotential += axon.getTransmittedPotential() ;
		}
		futurePotential *= this.membraneTransmissionFactor ;
	}
	

	public double getPotential(int index) { return currentPotential[index] ; }
	public double getPotential() { return currentPotential[clock] ; }
	public void setPotential(double potential) { this.futurePotential = potential; }
	
	public void addInput( Axon axon ) { inputs.add(axon) ; }
	public Iterator<Axon> iterator() { return inputs.iterator() ; }

	public double getMembraneTransmissionFactor() {	return membraneTransmissionFactor; }
	public void adjustMembraneTransmissionFactor(double membraneTransmissionFactorFactor ) {
		this.membraneTransmissionFactor += membraneTransmissionFactorFactor;
		if( this.membraneTransmissionFactor <= -1.0 ) {
			this.membraneTransmissionFactor = -1.0 ;
		}
		if( this.membraneTransmissionFactor >= 1.0 ) {
			this.membraneTransmissionFactor = 1.0 ;
		}
	}
	
	
	public int getIndexInBrain() { return indexInBrain; }
	
	public NeuronType getType() { return NeuronType.INTERNAL ; }
	
	public String toString() { return "[" + String.valueOf(getIndexInBrain()) + "]" + String.valueOf( getPotential() ) ; } 
}



enum NeuronType {
	INPUT, OUTPUT, INTERNAL ;
}