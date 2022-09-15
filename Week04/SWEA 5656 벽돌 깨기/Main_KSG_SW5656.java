package week4.sw5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_KSG_SW5656 {
	
	static int[] dr = {-1,1,0,0};
	
	static int[] dc = {0,0,-1,1};
	
	static int r;
	
	static int c;
	
	static int maxValue;
	
	static int initBlock;
	
	static boolean test(int i, int j) {
		return i>=0 && i<r && j>=0 && j<c;
	}
	
	static int[][][] copiedBoard;
	
	private static void copyBoard(int[][][] fullBoard, int[][] fullHighestBlock, int src, int dst) {
		int[] srcHighestBlock = fullHighestBlock[src];
		int[] dstHighestBlock = fullHighestBlock[dst]; 
		int[][] srcBoard = fullBoard[src];
		int[][] dstBoard = fullBoard[dst];
		for(int i=0; i<c; i++) {
			dstHighestBlock[i] = srcHighestBlock[i];
		}
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				dstBoard[i][j] = srcBoard[i][j];
			}
		}
		
	}
	
	static void simulate(int[][][] fullBoard, int[][] fullHighestBlock, int cnt, int maxCnt, int value) {
		if(cnt == maxCnt || initBlock == value) {
			maxValue = Math.max(maxValue,value);
			return;
		}
		int[] highestBlock = fullHighestBlock[cnt];
		for(int i=0; i<c; i++) {
			if(highestBlock[i] == r) continue;
			copyBoard(fullBoard,fullHighestBlock,cnt,cnt+1);
			int result = simulate(fullBoard[cnt+1],fullHighestBlock[cnt+1],i);
			simulate(fullBoard,fullHighestBlock, cnt+1, maxCnt, value+result);
		}
	}
	
	

	static int simulate(int[][] board, int[] highestBlock, int pos) {
		int result = breakBlock(board, highestBlock[pos], pos);
		gravity(board,highestBlock);
		return result;
	}
	
	static int breakBlock(int[][] board, int i, int j) {
		int sum = 1;
		int blockNum = board[i][j];
		board[i][j] = 0;
		
		for(int d=0; d<4; d++) {
			int ii = i;
			int jj = j;
			for(int cnt = 1; cnt < blockNum; cnt++) {
				ii = ii+dr[d];
				jj = jj+dc[d];
				if(!test(ii,jj)) break;
				
				if(board[ii][jj] > 0) {
					if(board[ii][jj] == 1) {
						board[ii][jj] = 0;
						sum++;
					}else {
						sum += breakBlock(board,ii,jj);
					}
				}
			
			}
		}
		return sum;
	}
	
	static void gravity(int[][] board, int[] highestBlock) {
		for(int j=0; j<c; j++) {
			int zCount = 0;
			for(int i=r-1; i>=0; i--) {
				if(board[i][j] == 0) {
					zCount++;
				}else if(zCount > 0){
					board[i+zCount][j] = board[i][j];
					board[i][j] = 0;
				}
			}
			highestBlock[j] = zCount;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			r = h;
			c = w;
			maxValue = 0;
			
			initBlock = 0;
			
			int[] highestBlock = new int[c];
			
			Arrays.fill(highestBlock, r);
			
			int[][] board = new int[r][c];
			
			for(int i=0; i<r; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<c; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] != 0) {
						initBlock++;
					}
				}
			}
			
			for(int j=0; j<c; j++) {
				for(int i=0; i<r; i++) {
					if(board[i][j] != 0) {
						highestBlock[j] = i;
						break;
					}
				}
			}
			int[][][] fullBoard = new int[n+1][][];
			int[][] fullHighestBlock = new int [n+1][];
			fullBoard[0] = board;
			fullHighestBlock[0] = highestBlock;
			for(int i=1; i<=n; i++) {
				fullBoard[i] = new int[r][c];
				fullHighestBlock[i] = new int[c];
			}
			
			simulate(fullBoard, fullHighestBlock, 0, n, 0);
			
			sb.append('#').append(t).append(' ').append(initBlock - maxValue).append('\n');
		}
		System.out.print(sb);
	}
}
