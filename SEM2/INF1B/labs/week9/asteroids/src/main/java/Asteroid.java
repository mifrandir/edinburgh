public class Asteroid {
    private double distance;
    private double theta;
    private double phi;
    private int speed;
    private SizeCategory size;

    public double getDistance() {
        return this.distance;
    }

    public double getTheta() {
        return this.theta;
    }

    public double getPhi() {
        return this.phi;
    }

    public int getSpeed() {
        return this.speed;
    }

    public SizeCategory getSize() {
        return this.size;
    }

    public Asteroid(double distance, double theta, double phi, int speed, SizeCategory size) {
        if (size == null) {
            throw new NullPointerException();
        }
        this.distance = distance;
        this.theta = theta;
        this.phi = phi;
        this.speed = speed;
        this.size = size;
    }

    @Override
    public boolean equals(Object aThat) {
        if (aThat == null) {
            return false;
        }
        if (!(aThat instanceof Asteroid)) {
            return false;
        }
        if (this == aThat) {
            return true;
        }
        Asteroid a = (Asteroid) aThat;
        return a.getSize() == this.size && a.getDistance() == this.distance
                && a.getTheta() == this.theta && a.getPhi() == this.phi
                && a.getSpeed() == this.speed;
    }

    private static int hashOffset = 257;

    @Override
    public int hashCode() {
        int hash = 0;
        hash = hash * hashOffset + (int) this.distance;
        hash = hash * hashOffset + (int) this.theta;
        hash = hash * hashOffset + (int) this.phi;
        hash = hash * hashOffset + (int) this.speed;
        int s;
        switch (size) {
            case SMALL:
                s = 0;
                break;
            case MEDIUM:
                s = 1;
                break;
            default:
                s = 2;
                break;
        }
        hash = hash * hashOffset + s;
        return hash;
    }
}