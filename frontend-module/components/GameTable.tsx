import { GameType } from '@/util/GameType';
import { useGetPlayersByGame } from '@/util/hooks/useGetPlayersByGame';
import { PlayerTable } from './PlayerTable';

export const GameTable = ({ game }: { game: GameType }) => {
  const { playersByGame, isPlayersByGameLoading } = useGetPlayersByGame(game);

  return <PlayerTable players={playersByGame} isAll={false} isLoading={isPlayersByGameLoading} />;
};
