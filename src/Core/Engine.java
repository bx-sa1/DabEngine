package Core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Components.CPhysics;
import Graphics.Window;
import Input.InputHandler;

public abstract class Engine implements Runnable {
	
	private static PhysicsEngine phys = new PhysicsEngine();
    private int width, height;
    private boolean fullscreen;
    private String title;
    private HashMap<Integer, Integer> hints = new HashMap<>();
    private Window mainWindow = null;
    private Thread t;
    private int finalFrames = 0;
    private int finalUpdates = 0;
    private InputHandler inputhandler;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public Engine(int width, int height, String title, HashMap<Integer, Integer> hints, boolean fullscreen) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.fullscreen = fullscreen;
        this.hints = hints;
    }

    public synchronized void start() {
        t = new Thread(this, "gameThread");
        try {
            t.start();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public synchronized void end() {
    	glfwDestroyWindow(mainWindow.getWin());
        glfwTerminate();
        System.exit(0);
    }

    public void run() {
        long lastTime = System.nanoTime();
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        double delta = 0.0;

        init();
        while (!glfwWindowShouldClose(mainWindow.getWin())) {
            if(mainWindow.isResized()){
                glViewport(0, 0, mainWindow.getWidth(), mainWindow.getHeight());
            }
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                update();
                phys.update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                finalFrames = frames;
                finalUpdates = updates;
                System.out.println(updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        end();
    }

    public void init() {

        if (!glfwInit()) {
            LOGGER.log(Level.SEVERE, "Window not initialized");
            System.exit(1);
        }

        mainWindow = new Window(width, height, title, hints, fullscreen);
        if(fullscreen) {
        	mainWindow.createFullScreenWindow(glfwGetPrimaryMonitor());
        }
        else {
        	mainWindow.createWindow();
        }
        mainWindow.showWindow();

        glViewport(0, 0, mainWindow.getWidth(), mainWindow.getHeight());
        
        inputhandler = new InputHandler();

        glfwSetKeyCallback(mainWindow.getWin(), inputhandler);
        glfwSetCursorPosCallback(mainWindow.getWin(), inputhandler);
        glfwSwapInterval(1);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_STENCIL_TEST);

    }

    public void update() {
        onUpdate(inputhandler);
        glfwPollEvents();
    }

    public void render() {

        onRender();

        glfwSwapBuffers(mainWindow.getWin());
    }
    
    public static void addToPhysics(CPhysics cPhysics) {
    	phys.addToPhysics(cPhysics);
    }

    public abstract void onRender();
    public abstract void onUpdate(InputHandler handler);

    public Window getMainWindow() {
        return mainWindow;
    }

    public int getFrames() {
        return finalFrames;
    }

    public int getUpdates() {
        return finalUpdates;
    }
}