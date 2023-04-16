import { useQuery } from 'react-query';
import { getAllPlayers } from '../services/playerApiService';

export const useGetAllPlayers = () => {
  const { data, isLoading } = useQuery('useGetAllPlayers', getAllPlayers);

  return { players: data ?? [], isPlayersLoading: isLoading };
};
