package com.rc.neurons ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rc.Genome;


public class NeuronRZ extends Neuron {

	final static Logger log = LoggerFactory.getLogger( NeuronRZ.class ) ;
	
	private static final double A = 0.1 ;
	private static final double B = 0.26 ;
	private static final double C = -65 ;
	private static final double D = 2 ;
	
	public NeuronRZ( int id ) {
		super( id, A, B, C, D, false ) ;
	}

	public NeuronRZ( Genome genome, int id ) {
		super( genome, id ) ;
	}	
}

