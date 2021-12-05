/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author: Sanviseth Chou, sc2070@rutgers.edu, sc2070
 *
 *************************************************************************/

import java.awt.Color;
import java.io.FileFilter;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {

        // WRITE YOUR CODE HERE
        this.collageDimension = 4;
        this.tileDimension = 100;
        this.original = new Picture(filename);
        this.collage = new Picture(tileDimension*collageDimension, tileDimension*collageDimension);
        int w = tileDimension*collageDimension;
        int h = tileDimension*collageDimension;
        for (int tcol = 0; tcol < w; tcol++){
            for(int trow = 0; trow < h; trow++){
                int scol = tcol * original.width() / w;
                int srow = trow * original.height() / h;
                Color color = original.get(scol, srow);
                collage.set(tcol, trow, color);
                
            }
        }


    
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {

        // WRITE YOUR CODE HERE
        this.collageDimension = cd;
        this.tileDimension = td;
        this.original = new Picture(filename);
        this.collage = new Picture (td*cd, td*cd);
        int w = tileDimension*collageDimension;
        int h = tileDimension*collageDimension;
        for (int tcol = 0; tcol < w; tcol++){
            for(int trow = 0; trow < h; trow++){
                int scol = tcol * original.width() / w;
                int srow = trow * original.height() / h;
                Color color = original.get(scol, srow);
                collage.set(tcol, trow, color);
                
            }
        }
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {

        // WRITE YOUR CODE HERE
        return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {

        // WRITE YOUR CODE HERE
        return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {

        // WRITE YOUR CODE HERE
        return original;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

        // WRITE YOUR CODE HERE
        return collage;
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {

        // WRITE YOUR CODE HERE
        this.original.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {

        // WRITE YOUR CODE HERE
        this.collage.show();
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture newPic = new Picture(filename);
        int w = tileDimension;
        int h = tileDimension;
        for (int tcol = 0; tcol < w; tcol++){
            for(int trow =0 ; trow < h; trow++){
                int scol = tcol * newPic.width() / w;
                int srow = trow * newPic.height() / h;
                Color color = newPic.get(scol, srow);
                collage.set(tcol+(tileDimension*collageCol), trow+(tileDimension*collageRow), color);
            }

        }
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {

        // WRITE YOUR CODE HERE
        int w = tileDimension;
        int h = tileDimension;
        for(int i=0; i<collageDimension*tileDimension; i+=tileDimension){
            for(int j=0; j<collageDimension*tileDimension; j+=tileDimension){
                for (int tcol = 0; tcol < w; tcol++){
                    for(int trow =0 ; trow < h; trow++){
                        int scol = tcol * original.width() / w;
                        int srow = trow * original.height() / h;
                        Color color = original.get(scol, srow);
                        collage.set(tcol+j, trow+i, color);
                    }
        
                }
            }
        }
    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see CS111 Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture newCollage = new Picture(collage);
        int w = tileDimension;
        int h = tileDimension;
        for (int tcol = 0; tcol < w; tcol++){
            for(int trow =0 ; trow < h; trow++){
                int scol = tcol * newCollage.width() / (w*collageDimension);
                int srow = trow * newCollage.height() / (h*collageDimension);
                Color color = newCollage.get(scol+(tileDimension*collageCol), srow+(tileDimension*collageRow));
                int r = color.getRed(); 
                int b = color.getBlue();
                int g = color.getGreen();
                if(component.equals("red")){
                    collage.set(tcol+(tileDimension*collageCol), trow+(tileDimension*collageRow), new Color(r, 0, 0));
                }
                if(component.equals("blue")){
                    collage.set(tcol+(tileDimension*collageCol), trow+(tileDimension*collageRow), new Color(0, 0, b));
                }
                if(component.equals("green")){
                    collage.set(tcol+(tileDimension*collageCol), trow+(tileDimension*collageRow), new Color(0, g, 0));
                }
            
                
            }

        }
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void grayscaleTile (int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture newOne = new Picture(collage);
        int w = tileDimension;
        int h = tileDimension;
        for (int tcol = 0; tcol < w; tcol++){
            for(int trow = 0; trow < h; trow++){
                int scol = tcol * newOne.width() / (w*collageDimension);
                int srow = trow * newOne.height() / (h*collageDimension);
                Color color = newOne.get(scol+(tileDimension*collageCol), srow+(tileDimension*collageRow));
                Color gray = Luminance.toGray(color);
                collage.set(tcol+(tileDimension*collageCol), trow+(tileDimension*collageRow), gray);
            }

        }
    }


    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {

        ArtCollage art = new ArtCollage(args[0], 200, 8); 
        art.makeCollage();
        art.replaceTile(args[1],1,1);
        art.colorizeTile("blue", 1, 1);
        //art.grayscaleTile(1, 1);
        art.showCollagePicture();

        //compile: javac ArtCollage.java
        //run: java ArtCollage Flo.jpg  Flo2.jpeg
    
       
        
    }
}
