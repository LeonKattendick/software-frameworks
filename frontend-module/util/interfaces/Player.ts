import {GameType} from '../GameType';
import {Match} from './Match';

export interface Player {
  player_id: string;
  name: string;
  game_type: GameType;
  global_kda: number;
  matches: Match[];
}
