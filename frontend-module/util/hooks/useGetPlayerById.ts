import { useQuery } from 'react-query';
import { getPlayerById } from '../services/playerApiService';

export const useGetPlayerById = (id: string) => {
  const { data, isLoading } = useQuery(['useGetPlayerById', id], () => getPlayerById(id), { enabled: id !== undefined });

  return { playerById: data ?? null, isPlayerByIdLoading: isLoading };
};
