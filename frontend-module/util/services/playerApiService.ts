import axios from 'axios';
import {GameType} from '../GameType';
import {getBaseUrl} from '../baseUrl';
import {Player} from '../interfaces/Player';

export const getAllPlayers = (): Promise<Player[]> => {
  return new Promise((resolve, reject) =>
    axios
      .get(`${getBaseUrl()}/player`)
      .then((r) => resolve(r.data))
      .catch((e) => reject(e))
  );
};

export const getPlayerById = (id: number): Promise<Player> => {
  return new Promise((resolve, reject) =>
    axios
      .get(`${getBaseUrl()}/player/${id}`)
      .then((r) => resolve(r.data))
      .catch((e) => reject(e))
  );
};

export const getPlayersByGame = (type: GameType): Promise<Player[]> => {
  return new Promise((resolve, reject) =>
    axios
      .get(`${getBaseUrl()}/player/game/${type}`)
      .then((r) => resolve(r.data))
      .catch((e) => reject(e))
  );
};
