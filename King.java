import java.util.ArrayList;
/**
 * Represents a Chess king
 * 
 * @author Abhinav Pappu
 */
public class King implements Piece
{
    private boolean color, isInDanger, touched;
    private int row, col;
    
    /**
     * 
     * @param color color of piece (true - white, false - black)
     * 
     */
    public King(boolean color, int row, int col)
    {
        this.color = color;
        move(row, col);
        touched = false;
    }
    
    public int getValue(){
        return 10;
    }
    
    public int getNum(){
        return color? 5 : 11;
    }
    
    public String getIcon(){
        return "\u265A";
    }
    
    public boolean getColor(){
        return color;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return col;
    }
    
    public boolean isInDanger(){
        return isInDanger;
    }
    
    public void setInDanger(boolean inDanger){
        isInDanger = inDanger;
    }
    
    public boolean touched(){
        return touched;
    }
    
    public ArrayList<Move> getMoves(Board board){
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int j = -1; j <= 1; j++){
            for(int k = -1; k <= 1; k++){
                if(j == 0 && k == 0)
                    continue;
                int cR = j; //change in row
                int cC = k; //change in column
                if(row + cR > 7 || row + cR < 0 || col + cC > 7 || col + cC < 0){ //outside of board
                }
                else if(board.getPiece(row + cR, col + cC) == null){ //no piece at spot
                    moves.add(new Move(row + cR, col + cC, row, col));
                }
                else if(board.getPiece(row + cR, col + cC).getColor() == color){ //piece of same color at spot
                }
                else{ //enemy piece at spot
                    moves.add(new Move(row + cR, col + cC, row, col));
                }
            }
        }
        if(SpecialMove.checkCastleValidity(board, board.getPlayerColor(), color, true)){
            moves.add(new SpecialMove(color, true, board.getPlayerColor()));
        }
        if(SpecialMove.checkCastleValidity(board, board.getPlayerColor(), color, false)){
            moves.add(new SpecialMove(color, false, board.getPlayerColor()));
        }
        return moves;
    }
    
    public Piece move(int row, int col){
        touched = true;
        this.row = row;
        this.col = col;
        return this;
    }
    
    public String toString(){
        return ((color)? "White" : "Black") + " King";
    }
    
    public Piece clone(){
        return new King(color, row, col);
    }
}
