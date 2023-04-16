import axios from 'axios';
import { GameType } from '../GameType';
import { Player } from '../interfaces/Player';

export const getAllPlayers = (): Promise<Player[]> => {
  return new Promise((resolve, reject) =>
    axios
      .get('http://localhost:8090/player')
      .then((r) => resolve(r.data))
      .catch((e) => reject(e))
  );
};

export const getPlayerById = (id: string): Promise<Player> => {
  return new Promise((resolve, reject) =>
    axios
      .get(`http://localhost:8090/player/${id}`)
      .then((r) => resolve(r.data))
      .catch((e) => reject(e))
  );
};

export const getPlayersByGame = (type: GameType): Promise<Player[]> => {
  return new Promise((resolve, reject) =>
    axios
      .get(`http://localhost:8090/player/game/${type}`)
      .then((r) => resolve(r.data))
      .catch((e) => reject(e))
  );
};
