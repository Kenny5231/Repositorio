/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacion.pkg1__;

/**
 *
 * @author Kenny
 */

/**
 * Demo(): Este es el constructor de la clase Demo. Cuando se crea una instancia de esta clase, realiza las siguientes acciones:

Llama al constructor de la clase base Gameutilizando super("BattleShip Game in Demo Mode")para establecer el título de la ventana del juego como "BattleShip Game in Demo Mode".

LuegoThreadythis(Demo) como

public void run():Runnable.

Verifique si el sonido está habilitado en la clase BattleShipllamando al método soundOn(). Si el sonido está habilitado, reproduzca un sonido utilizando Sound.start.play(). La implementación específica de estos métodos no se muestra en el fragmento de código, pero sugiere que el juego tiene efectos de sonido.

Llama alplaceShips()en el objeto myField. Este método probablemente establece la ubicación inicial de los barcos.myFieldyplaceShips()no se proporciona en el

eswhile (demoRunning),demoRunningmartrue. El código dentro de este bu

Obtiene un punto (probablemente la entrada del usuario o una selección automática) del objeto myFieldutilizando `mymyField.getPoint().

lmyField.getHit(thePoint)para simresult.

Si el resultado es mayor que 0, llama a myField.setResult(thePoint, result) para registrar el resultado del impacto. Esto probablemente actualiza el tablero de juego con el resultado del impacto.

Después de cada iteración del bucle, hay una breve pausa utilizando Thread.sleep(10) para controlar la temporización de las acciones del juego.

SiInterruptedExceptiondurante

En resumen, este código parece ser parte de un ciclo de juego que simula la jugabilidad para un juego de Batalla Naval en modo demostración. Los detalles específicos de la mecánica del juego y las implementaciones de las clases BattleShip, Sound, myField y los métodos relacionados no se proporcionan en el fragmento de código.
 * 
 */
//no estendi esto
class Demo extends Game implements Runnable
{
	Demo()
	{
		super("BattleShip Game in Demo Mode");
		new Thread(this, "GameDemo").start();
	}

	public void run()
	{
		if (BattleShip.soundOn()) Sound.start.play();
		myField.placeShips();
		while (demoRunning)
		{
			thePoint = myField.getPoint();
			result = myField.getHit(thePoint);		//In real game this is sent to & recieved from opponent
			if (result>0) myField.setResult(thePoint, result);
			try	{	Thread.sleep(10);	}
			catch	(InterruptedException ie)	{	ie.printStackTrace();	}
		}
	}
}

