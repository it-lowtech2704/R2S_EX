    package gof.com.lab3;

    import java.util.Scanner;

    public class Vase extends Item {
    private int height;
    private int material;

    public Vase() {
        super();
    }
    public Vase(String id, int value, String creator, int height, int material) {
        super(id, value, creator);
        this.height = height;
        this.material = material;
    }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getMaterial() {
            return material;
        }

        public void setMaterial(int material) {
            this.material = material;
        }

        public void input() {
            super.input();

            // Nhập thêm thông tin riêng của Vase
            while(true) {
                try {
                    System.out.print("Enter height: ");
                    height = Integer.parseInt(sc.nextLine());
                    if (height > 0) break;
                    else System.out.println("Height must be greater than 0.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please try again.");
                }
            }

            while(true) {
                try {
                    System.out.print("Enter material: ");
                    material = Integer.parseInt(sc.nextLine());
                    if (material > 0) break;
                    else System.out.println("Material must be greater than 0.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please try again.");
                }
            }
        }


        @Override
        public String toString() {
            return "[Vase] " + super.toString() + ", Height: " + height + ", Material: " + material;
        }
    }
