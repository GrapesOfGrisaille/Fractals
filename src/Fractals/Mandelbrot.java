package Fractals;

public class Mandelbrot extends Fractal{

    private static final int ITERATIONS = 100;

    private boolean multicorn; // Is it a multicorn?

    public Mandelbrot(double xOrigin, double xEnd, double yOrigin, double yEnd, int panelWidth, int panelHeight, int exponent, boolean multicorn){
        super(xOrigin, xEnd, yOrigin, yEnd, panelWidth, panelHeight, exponent);
        this.multicorn = multicorn;
    }
    // Return type is intensity of pixel at current point, based on # iterations
    int calcPoint(double x, double y){
        ComplexNum z0 = new ComplexNum(x, y);
        ComplexNum zn = z0;
        for(int i = 0; i < ITERATIONS; i++){
            if(multicorn){
                zn = zn.conjugate().power(exponent).add(z0); // z_n+1 = conj(z_n)^2 + z_0
            }
            else{
                zn = zn.power(exponent).add(z0); // z_n+1 = z_n^2 + z_0
            }
            if(zn.magnitude() > 2.0){
                return 255-255*i/ITERATIONS;
            }
        }
        return 0;
    }
    
    public void render(){
        // Designed to reduce floating point error accumulation at higher res
        double stepSizeX = (xEnd - xOrigin) / imageWidth;
        double stepSizeY = (yEnd - yOrigin) / imageHeight;
        double x, y;
        for(int i = 0; i < imageWidth; i++){
            x = xOrigin + ((double)i * stepSizeX);
            for(int j = 0; j < imageHeight; j++){
                y = yOrigin + ((double)j * stepSizeY);
                // Calculate 
                int color = calcPoint(x, y);
                plot.setRGB(i, j, color);
            }
        }
    }
}
