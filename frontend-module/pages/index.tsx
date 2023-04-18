import { PlayerTable } from '@/components/PlayerTable';
import { useGetAllPlayers } from '@/util/hooks/useGetAllPlayers';
import { Typography } from 'antd';

const Home = () => {
  const { players } = useGetAllPlayers();

  return (
    <>
      <Typography.Title>Alle Spieler</Typography.Title>
      <PlayerTable players={players} isAll={true} />
    </>
  );
};

export default Home;
