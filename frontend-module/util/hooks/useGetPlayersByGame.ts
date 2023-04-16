import { useQuery } from 'react-query';
import { GameType } from '../GameType';
import { getPlayersByGame } from '../services/playerApiService';

export const useGetPlayersByGame = (type: GameType) => {
  const { data, isLoading } = useQuery(['useGetPlayersByGame', type], () => getPlayersByGame(type), { enabled: type !== undefined });

  return { playersByGame: data ?? [], isPlayersByGameLoading: isLoading };
};
