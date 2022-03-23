package controller;

import java.util.concurrent.Semaphore;

public class Pessoa extends Thread {
	private int velocidade, distancia_percorrida;
	private int tempo_cruzamento;
	private int tamanho_corredor = 20;
	
	private Semaphore semaforo_corredor;
	
	public Pessoa(Semaphore _corredor) {
		semaforo_corredor = _corredor;
		velocidade = (int) ((Math.random() * 3) + 4);
		tempo_cruzamento = (int) ((Math.random() * 1001) + 1000);
	}
	
	public void run() {
		andar();
		
		try {
			semaforo_corredor.acquire();
			atravessarPorta();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			semaforo_corredor.release();
		}
		
	}
	
	private void andar() {
		System.out.println("#"+getId() + " ANDANDO até a porta.");
		while(distancia_percorrida < tamanho_corredor) {
			try {
				distancia_percorrida += velocidade;
				System.out.println("#"+getId()+" percorreu " + distancia_percorrida + " metros.");
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void atravessarPorta() {
		System.out.println("#"+getId() + " ATRAVESSANDO a porta.");
		
		try {
			sleep(tempo_cruzamento);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#"+getId() + " SAIU.");
	}
}
