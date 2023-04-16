import { useGetAllPlayers } from '@/util/hooks/useGetAllPlayers';
import { Button } from 'antd';

const Home = () => {
  const { players } = useGetAllPlayers();

  return (
    <>
      <Button>test</Button>
    </>
  );
};

export default Home;
