package main;

import java.awt.EventQueue;

import views.AplicacionGrafica;

public class MainApp {

	public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	new AplicacionGrafica();
	            }
	        });
	}
}
