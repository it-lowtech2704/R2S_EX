package gof.com.lab3;

public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWatercolor;
    private boolean isFramed;

    public Painting() {
        super();
    }

    public Painting(String id, int value, String creator, int height, int width, boolean isWatercolor, boolean isFramed) {
        super(id, value, creator);
        this.height = height;
        this.width = width;
        this.isWatercolor = isWatercolor;
        this.isFramed = isFramed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isWatercolor() {
        return isWatercolor;
    }

    public void setWatercolor(boolean watercolor) {
        isWatercolor = watercolor;
    }

    public boolean isFramed() {
        return isFramed;
    }

    public void setFramed(boolean framed) {
        isFramed = framed;
    }

    @Override
    public void input() {
        super.input(); // Gọi input() của lớp Item

        // Nhập thông tin riêng của Painting
        while(true) {
            try {
                System.out.print("Enter height: ");
                height = Integer.parseInt(sc.nextLine());
                System.out.print("Enter width: ");
                width = Integer.parseInt(sc.nextLine());
                if (height > 0 && width > 0) break;
                else System.out.println("Height and Width must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }

        while(true) {
            System.out.print("Is it watercolor? (Y/N): ");
            String wc = sc.nextLine();
            if (wc.equalsIgnoreCase("Y")) {
                isWatercolor = true;
                break;
            } else if (wc.equalsIgnoreCase("N")) {
                isWatercolor = false;
                break;
            } else {
                System.out.println("Please enter Y (Yes) or N (No).");
            }
        }

        while(true) {
            System.out.print("Is it framed? (Y/N): ");
            String fr = sc.nextLine();
            if (fr.equalsIgnoreCase("Y")) {
                isFramed = true;
                break;
            } else if (fr.equalsIgnoreCase("N")) {
                isFramed = false;
                break;
            } else {
                System.out.println("Please enter Y (Yes) or N (No).");
            }
        }
    }

    @Override
    public String toString() {
        return "[Painting] " + super.toString() + ", Height: " + height + ", Width: " + width +
               ", Watercolor: " + (isWatercolor ? "Yes" : "No") + ", Framed: " + (isFramed ? "Yes" : "No");
    }
}
