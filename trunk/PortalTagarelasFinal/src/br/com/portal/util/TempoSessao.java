package br.com.portal.util;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import br.com.portal.chat.tipico.controller.SalaChatBean;

public class TempoSessao {

	Toolkit toolkit;

	  Timer timer;

	  public TempoSessao(int seconds) {
	    toolkit = Toolkit.getDefaultToolkit();
	    timer = new Timer();
	    timer.schedule(new RemindTask(), seconds * 1000);
	  }

	  class RemindTask extends TimerTask {
	    public void run() {
	      System.out.println("Time's up!");
	      SalaChatBean sala = new SalaChatBean();
	      sala.alertaTempo();
	      toolkit.beep();
	      timer.cancel(); //Not necessary because we call System.exit
	     // System.exit(0); //Stops the AWT thread (and everything else)
	    }
	  }

//	  public static void main(String args[]) {
//	    System.out.println("About to schedule task.");
//	    new TempoSessao(5);
//	    System.out.println("Task scheduled.");
//	  }
	
	
}
